# Add project specific ProGuard rules here.
# For now minification is disabled (isMinifyEnabled = false), so these rules are
# placeholders that become active once R8 shrinking is enabled.

# Keep Room entity/DAO generated code.
-keep class com.office.data.database.** { *; }

# Keep Hilt generated components.
-keep class dagger.hilt.** { *; }
-keep class com.office.android.** { *; }
