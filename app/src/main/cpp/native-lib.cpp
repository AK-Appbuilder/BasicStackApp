#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

Java_com_example_basicstackapp_common_Keys_cipherKey(JNIEnv *env, jobject object) {
    std::string api_key = "fdafda";
    return env->NewStringUTF(api_key.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_basicstackapp_common_Keys_vectorSecretKey(JNIEnv *env, jobject object) {
    std::string api_key = "customer-app";
    return env->NewStringUTF(api_key.c_str());
}
