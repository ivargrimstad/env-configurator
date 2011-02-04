/*
Copyright [2011] [Ivar Grimstad (ivar.grimstad@gmail.com)]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package eu.agilejava.mojo.envconfigurator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal update
 * @phase deploy
 * @description Updates existing configurations. Does not remove anything.
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
public class UpdateConfiguratonMojo extends AbstractMojo {

   @Override
   public void execute() throws MojoExecutionException, MojoFailureException {
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
