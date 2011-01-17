#
# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#
# This is the product configuration for a generic GSM passion,
# not specialized for any geography.
#

# General Network properties for verizon

PRODUCT_PROPERTY_OVERRIDES += \
	ro.com.android.wifi-watchlist=GoogleGuest \
	ro.error.receiver.system.apps=com.google.android.feedback \
	ro.setupwizard.enterprise_mode=1 \
	ro.com.google.clientidbase=android-verizon \
	ro.com.google.locationfeatures=1 \
	ro.url.legal=http://www.google.com/intl/%s/mobile/android/basic/phone-legal.html \
	ro.url.legal.android_privacy=http://www.google.com/intl/%s/mobile/android/basic/privacy.html \
	ro.cdma.home.operator.numeric=310004 \
	ro.cdma.home.operator.alpha=Verizon \
	ro.cdma.homesystem=64,65,76,77,78,79,80,81,82,83 \
	ro.cdma.data_retry_config=default_randomization=2000,0,0,120000,180000,540000,960000 \
	ro.config.vc_call_vol_steps=7 \
	ro.cdma.otaspnumschema=SELC,1,80,99 \
	ro.telephony.call_ring.multiple=false \
	ro.telephony.call_ring.delay=3000 \
	ro.url.safetylegal=http://www.htc.com/staticfiles/Support/legal/?model=A855 \
	ro.setupwizard.enable_bypass=1 \
	ro.media.dec.jpeg.memcap=20000000 \


# Default network type.
# 0 => WCDMA preferred.
PRODUCT_PROPERTY_OVERRIDES += \
    ro.telephony.default_network=0

PRODUCT_COPY_FILES += \
    device/htc/inc/Config/vold.fstab:system/etc/vold.fstab \
    device/htc/inc/Config/gps.conf:system/etc/gps.conf \
    device/htc/inc/Config/apns-conf.xml:system/etc/apns-conf.xml



#
# Firmware
PRODUCT_COPY_FILES += \
    device/htc/inc/firmware/bcm4329.hcd:system/etc/firmware/bcm4329.hcd \
    device/htc/inc/firmware/fw_bcm4329.bin:system/etc/firmware/fw_bcm4329.bin \
    device/htc/inc/firmware/fw_bcm4329_apsta.bin:system/etc/firmware/fw_bcm4329_apsta.bin
#
# Permissions
#
#

PRODUCT_COPY_FILES += \
    frameworks/base/data/etc/handheld_core_hardware.xml:system/etc/permissions/handheld_core_hardware.xml \
    frameworks/base/data/etc/android.hardware.camera.flash-autofocus.xml:system/etc/permissions/android.hardware.camera.flash-autofocus.xml \
    frameworks/base/data/etc/android.hardware.telephony.gsm.xml:system/etc/permissions/android.hardware.telephony.gsm.xml \
    frameworks/base/data/etc/android.hardware.location.gps.xml:system/etc/permissions/android.hardware.location.gps.xml \
    frameworks/base/data/etc/android.hardware.wifi.xml:system/etc/permissions/android.hardware.wifi.xml \
    frameworks/base/data/etc/android.hardware.sensor.proximity.xml:system/etc/permissions/android.hardware.sensor.proximity.xml \
    frameworks/base/data/etc/android.hardware.sensor.light.xml:system/etc/permissions/android.hardware.sensor.light.xml \
    frameworks/base/data/etc/android.hardware.touchscreen.multitouch.xml:system/etc/permissions/android.hardware.touchscreen.multitouch.xml


#
#
# Kernel for the incredible
#
#
ifeq ($(TARGET_PREBUILT_KERNEL),)
LOCAL_KERNEL := device/htc/inc/kernel
else
LOCAL_KERNEL := $(TARGET_PREBUILT_KERNEL)
endif


PRODUCT_COPY_FILES += \
    $(LOCAL_KERNEL):kernel


#
#
# Kernel modules
PRODUCT_COPY_FILES += \
    device/htc/inc/modules/bcm4329.ko:system/lib/modules/bcm4329.ko


#
# Overlay
#
DEVICE_PACKAGE_OVERLAYS := \
      device/htc/inc/overlay

#
# Product packages
#
PRODUCT_PACKAGES += \
    sensors.inc \
    lights.inc \
    librs_jni









