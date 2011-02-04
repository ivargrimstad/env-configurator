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

import org.junit.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;

import static org.powermock.api.easymock.PowerMock.*;
import static org.powermock.reflect.Whitebox.*;

/**
 *
 * @author Ivar grimstad (ivar.grimstad@gmail.com)
 */
@RunWith(PowerMockRunner.class)
@MockPolicy(Slf4jMockPolicy.class)
public class UpdateConfiguratonMojoTest extends TestCase {

   private UpdateConfiguratonMojo instance = new UpdateConfiguratonMojo();

   @Before
   public void setup() {
   }
   
   /**
    * Test of execute method, of class UpdateConfiguratonMojo.
    */
   @Test
   public void testExecute() throws Exception {
      
      replayAll();
      instance.execute();
      verifyAll();
   }
}
