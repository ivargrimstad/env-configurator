/*
 * Copyright [2011] [Ivar Grimstad (ivar.grimstad@gmail.com)]
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Abstract base class for configuration mojos.
 * 
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
public abstract class AbstractConfigurationMojo extends AbstractMojo {

   /**
    * @parameter expression="${environment}" default-value="dev"
    */
   private String environment;
   /**
    * @parameter expression="${template}" default-value="${basedir}${file.separator}src${file.separator}main${file.separator}resources${file.separator}template.properties"
    */
   private String template;

   @Override
   public void execute() throws MojoExecutionException, MojoFailureException {
      getLog().info("Environment: " + environment);
      getLog().info("Template: " + template);
      doExecute();
   }

   public void setEnvironment(String environment) {
      this.environment = environment;
   }

   public void setTemplate(String template) {
      this.template = template;
   }

   /**
    * Executes the plugin.
    * 
    * @throws MojoExecutionException if execution fails
    * @throws MojoFailureException  if mojo fails
    */
   protected abstract void doExecute() throws MojoExecutionException, MojoFailureException;
}
