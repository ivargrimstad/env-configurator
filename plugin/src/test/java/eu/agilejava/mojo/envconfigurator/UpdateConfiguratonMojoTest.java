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

import java.lang.UnsupportedOperationException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author ivar
 */
@RunWith(JUnit4ClassRunner.class) // TODO: Remove when UnsupportedOperationException is not expected...
public class UpdateConfiguratonMojoTest extends TestCase {

   /**
    * Test of execute method, of class UpdateConfiguratonMojo.
    */
   @Test(expected=UnsupportedOperationException.class)
   public void testExecute() throws Exception {
      UpdateConfiguratonMojo instance = new UpdateConfiguratonMojo();
      instance.execute();
   }
}
