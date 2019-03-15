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
package com.iris.platform.subsystem.placemonitor.offlinenotifications;

import static com.iris.messages.capability.PlaceMonitorSubsystemCapability.ATTR_LOWBATTERYNOTIFICATIONSENT;

import java.util.Date;
import java.util.Map;

import com.google.inject.Singleton;
import com.iris.common.subsystem.SubsystemContext;
import com.iris.messages.model.Model;
import com.iris.messages.model.dev.DevicePowerModel;
import com.iris.messages.model.subs.PlaceMonitorSubsystemModel;

@Singleton
public class LowBatteryNotificationsHandler extends AbstractLowBatteryNotificationsHandler
{
   @Override
   protected String getNotificationSentAttribute()
   {
      return ATTR_LOWBATTERYNOTIFICATIONSENT;
   }

   @Override
   protected boolean isBatteryTypeSupported(boolean rechargeable)
   {
      // Low Battery notifications must support both rechargeable and non-rechargeable batteries
      return true;
   }

   @Override
   protected int getNotificationThreshold(String productId)
   {
      return notificationThresholdsConfig.get().getBatteryLow(productId);
   }

   @Override
   protected int getNextNotificationThreshold(String productId)
   {
      return notificationThresholdsConfig.get().getBatteryVeryLow(productId);
   }

   @Override
   protected int getNotificationClearThreshold(String productId)
   {
      return notificationThresholdsConfig.get().getBatteryLowClear(productId);
   }

   @Override
   protected Map<String, Date> getSentNotificationsDeviceMap(PlaceMonitorSubsystemModel model)
   {
      return model.getLowBatteryNotificationSent();
   }

   @Override
   protected void sendNotification(Model device, SubsystemContext<PlaceMonitorSubsystemModel> context)
   {
      boolean rechargeable = DevicePowerModel.getRechargeable(device, false);

      if (!rechargeable)
      {
         notifier.sendDeviceHasALowBattery(device, context);
      }
      else
      {
         notifier.sendDeviceHasALowRechargeableBattery(device, context);
      }
   }
}
