LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

include ../../../OpenCV-2.4.8-android-sdk/sdk/native/jni/OpenCV.mk

LOCAL_MODULE    := TopView
LOCAL_SRC_FILES := topview.cpp lane.cpp LaneDetect.cpp
LOCAL_LDLIBS +=  -llog -ldl

include $(BUILD_SHARED_LIBRARY)
