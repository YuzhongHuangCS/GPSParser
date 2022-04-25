# GPSParser
GPSParser

An parser and decrypter to extract GPS info from dash cameras like iiwey s1.

These cameras use proprietary format to store GPS info, and can not be exported to open formats in their offifical app.

The proprietary format could be identified by file header "LIGOGPSINFO", and having 132 bytes in each data block.

I decomplied their android app and write a simple wrapper around `libsungps.so` to decrypt the proprietary format.

References:
1. https://exiftool.org/forum/index.php?topic=13115.0
2. https://www.6zhentan.com/opendoc/pages/gps/data.html
3. http://www.sunningsoft.com/zh/pcgps.html
4. https://play.google.com/store/apps/details?id=com.zj.witcampro

An example of source log and decoded log is in folder `example`

A complied APK is in folder `apk`. It needs write file system permission.
