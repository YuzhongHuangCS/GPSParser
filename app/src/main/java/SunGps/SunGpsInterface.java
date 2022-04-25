package SunGps;

public class SunGpsInterface {
    public native int MakeEncryptDataBlock(int i, byte[] bArr, int i2, byte[] bArr2);

    public native int MakeEncryptDataBlockII(byte[] bArr, int i, byte[] bArr2);

    public native int MakeHeader(byte[] bArr, int i);

    public native int MakeRawDataBlock(int i, byte[] bArr, int i2, byte[] bArr2);

    public native void SunDecodeInfo(byte[] bArr);

    public native int SunDecrypt(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2);

    public native int SunEncrypt(byte[] bArr, int i, byte[] bArr2, byte[] bArr3);

    public native int SunGetEncType();

    public native int SunGetEyeFishID();

    public native int SunGetFileType();

    public native int SunSetEncType(int i);

    public native void SunSetFileInfo(byte[] bArr, int i, int i2);

    public native String sunGpsDecode(String str, int i);

    public native void sunGpsExit();

    public native int[] sunGpsGetSunTimer(float f, float f2, int i, int i2, int i3, int i4);

    public native void sunGpsInit();

    public native void sunGpsSetDecodeType(int i);

    static {
        System.loadLibrary("sungps");
    }
}
