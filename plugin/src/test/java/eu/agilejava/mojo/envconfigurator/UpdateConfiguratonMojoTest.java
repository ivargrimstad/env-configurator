/**
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

import java.util.Collections;
import java.io.File;
import org.junit.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.easymock.PowerMock.*;
import static org.powermock.reflect.Whitebox.*;
import static org.easymock.EasyMock.expect;

/**
 *
 * @author Ivar grimstad (ivar.grimstad@gmail.com)
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UpdateConfiguratonMojo.class)
public class UpdateConfiguratonMojoTest extends TestCase {

   private File inMock = createMock(File.class);
   private File outMock = createMock(File.class);
   private UpdateConfiguratonMojo instance = new UpdateConfiguratonMojo();

   @Before
   public void setup() {
      setInternalState(instance, "environment", "dev");
   }

   /**
    * Test of execute method, of class UpdateConfiguratonMojo.
    */
   @Test
   public void testExecute() throws Exception {

      File[] files = new File[] {};
      
      expectNew(File.class, "/home/ivar/Development/personal/env-configurator/sample/src/main/resources/dev").andReturn(inMock);
      expect(inMock.listFiles()).andReturn(files);
      
      replayAll();
      instance.execute();
      verifyAll();
   }
}
