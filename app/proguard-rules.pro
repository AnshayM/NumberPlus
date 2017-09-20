# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html


-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Dont warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

#以上是系统默认混淆文档，以下是个人配置文档

#去掉警告（因为有些class并不使用但会报出警告）
-dontwarn okio.**

#不混淆三方库代码
-keep class android.support.** {*;} # 不混淆support包下的类
-keep class com.** {*;}# 不混淆com包下的类
-keep class okhttp3.** {*;}# 不混淆okhttp3包下的类
-keep class okio.** {*;}# 不混淆okio包下的类
-keep class okio.** {*;}# 不混淆okio包下的类

-keep class anshay.numberplus.Bean.** {*;}  #不混淆实体类
-keep class anshay.numberplus.gson.** {*;}  #不混淆实体类

