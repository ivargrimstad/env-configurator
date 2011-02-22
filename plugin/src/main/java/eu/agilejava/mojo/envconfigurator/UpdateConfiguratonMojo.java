/**
 * Copyright 2011 Ivar Grimstad (ivar.grimstad@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.agilejava.mojo.envconfigurator;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal update
 * @phase install
 * @description Updates existing configurations. Does not remove anything.
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
public class UpdateConfiguratonMojo extends AbstractConfigurationMojo {

   /**
    * @see AbstractConfigurationMojo#doExecute() 
    */
   @Override
   public void doExecute() throws MojoExecutionException, MojoFailureException {
      getLog().info("doExecute called");

      File sourceFolder = new File("/home/ivar/Development/personal/env-configurator/sample/src/main/resources/" + getEnvironment());
      String targetFolder = "/home/ivar/Development/personal/env-configurator/sample/target";

      for (File file : sourceFolder.listFiles()) {

         try {
            
            Properties props = new Properties();
            props.load(new FileInputStream(file));
            
            System.out.println(props.keySet());
            
//            copy(file, new File(targetFolder + File.separator + file.getName()));
            System.setProperties(props);

         } catch (IOException e) {
            getLog().error("Error copying file: " + file.getName());
            throw new MojoExecutionException("jalla" + e);
         }
      }
      
      System.out.println(System.getProperty("property-one"));

      getLog().info("Finished!");

   }

   private void copy(File source, File target) throws IOException {

      InputStream in = new FileInputStream(source);
      OutputStream out = new FileOutputStream(target);

      // Copy the bits from instream to outstream
      byte[] buf = new byte[1024];
      int len;

      while ((len = in.read(buf)) > 0) {
         out.write(buf, 0, len);
      }

      in.close();
      out.close();
   }

   private void scp(String[] arg) {
      FileInputStream fis = null;
      try {

         String lfile = arg[0];
         String user = arg[1].substring(0, arg[1].indexOf('@'));
         arg[1] = arg[1].substring(arg[1].indexOf('@') + 1);
         String host = arg[1].substring(0, arg[1].indexOf(':'));
         String rfile = arg[1].substring(arg[1].indexOf(':') + 1);

         JSch jsch = new JSch();
         Session session = jsch.getSession(user, host, 22);

         // username and password will be given via UserInfo interface.
         UserInfo ui = new UserInfo()  {

            @Override
            public String getPassphrase() {
               return "";
            }

            @Override
            public String getPassword() {
               return "";
            }

            @Override
            public boolean promptPassphrase(String string) {
               return Boolean.FALSE;
            }

            @Override
            public boolean promptPassword(String string) {
               return Boolean.FALSE;
            }

            @Override
            public boolean promptYesNo(String string) {
               return Boolean.FALSE;
            }

            @Override
            public void showMessage(String string) {
            }
         };
         
         session.setUserInfo(ui);
         session.connect();

         // exec 'scp -t rfile' remotely
         String command = "scp -p -t " + rfile;
         Channel channel = session.openChannel("exec");
         ((ChannelExec) channel).setCommand(command);

         // get I/O streams for remote scp
         OutputStream out = channel.getOutputStream();
         InputStream in = channel.getInputStream();

         channel.connect();

         if (checkAck(in) != 0) {
            System.exit(0);
         }

         // send "C0644 filesize filename", where filename should not include '/'
         long filesize = (new File(lfile)).length();
         command = "C0644 " + filesize + " ";
         if (lfile.lastIndexOf('/') > 0) {
            command += lfile.substring(lfile.lastIndexOf('/') + 1);
         } else {
            command += lfile;
         }
         command += "\n";
         out.write(command.getBytes());
         out.flush();
         if (checkAck(in) != 0) {
            System.exit(0);
         }

         // send a content of lfile
         fis = new FileInputStream(lfile);
         byte[] buf = new byte[1024];
         while (true) {
            int len = fis.read(buf, 0, buf.length);
            if (len <= 0) {
               break;
            }
            out.write(buf, 0, len); //out.flush();
         }
         fis.close();
         fis = null;
         // send '\0'
         buf[0] = 0;
         out.write(buf, 0, 1);
         out.flush();
         if (checkAck(in) != 0) {
            System.exit(0);
         }
         out.close();

         channel.disconnect();
         session.disconnect();

         System.exit(0);
      } catch (Exception e) {
         System.out.println(e);
         try {
            if (fis != null) {
               fis.close();
            }
         } catch (Exception ee) {
         }
      }
   }

   private int checkAck(InputStream in) throws IOException {
      int b = in.read();
      // b may be 0 for success,
      //          1 for error,
      //          2 for fatal error,
      //          -1
      if (b == 0) {
         return b;
      }
      if (b == -1) {
         return b;
      }

      if (b == 1 || b == 2) {
         StringBuffer sb = new StringBuffer();
         int c;
         do {
            c = in.read();
            sb.append((char) c);
         } while (c != '\n');
         if (b == 1) { // error
            System.out.print(sb.toString());
         }
         if (b == 2) { // fatal error
            System.out.print(sb.toString());
         }
      }
      return b;
   }
}
