package rsa;

import math.SquareAndMultiply;

import java.math.BigInteger;

public class CharacterTransformer implements InputTransformer {
    @Override
    public byte[] transform(BigInteger exp, BigInteger mod, byte[] msg) {
        BigInteger message = new BigInteger(msg);
        BigInteger temp = SquareAndMultiply.squareAndMultiply(message, exp, mod);
        return temp.toByteArray();
    }
}
