# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\kehooo\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn android.support.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.okio.**
-dontwarn okio.**
-dontwarn com.startapp.**
-dontwarn com.google.android.gms.**
-keep public class com.google.android.gms.**
-dontwarn com.onesignal.**
-dontwarn onesignal.**
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
  static ** CREATOR;
}
-keep public class com.bumptech.glide.**
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
