/*
 * Copyright 2019 Arcus Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iris.agent.zw.code.entity;

import com.iris.agent.util.ByteUtils;
import com.iris.agent.zw.code.cmdclass.BasicCmdClass;
import com.iris.agent.zw.code.cmdclass.CmdClasses;

/**
 * Basic Get
 * 
 * 2 Bytes
 * 
 * 0     : CmdClass (0x20)
 * 1     : Cmd (0x02)
 *
 * @author Erik
 */
public class CmdBasicGet extends AbstractZCmd {
   private final static int BYTE_LENGTH = 2;
   
   public final static CmdBasicGet CMD_BASIC_GET = new CmdBasicGet();
   
   public CmdBasicGet() {
      super(CmdClasses.BASIC.intId(), BasicCmdClass.CMD_BASIC_GET, BYTE_LENGTH);
   }

   @Override
   public byte[] bytes() {
      return ByteUtils.ints2Bytes(cmdClass, cmd);
   }
}


