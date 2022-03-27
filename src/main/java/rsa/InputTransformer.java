package rsa;

import java.math.BigInteger;

public interface InputTransformer {
    byte[] transform(BigInteger exp, BigInteger mod, byte[] msg);
}
