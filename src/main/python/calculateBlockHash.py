from hashlib import sha256
import binascii

DATA = "0100000081cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000e320b6c2fffc8d750423db8b1eb942ae710e951ed797f7affc8892b0f1fc122bc7f5d74df2b9441a42a14695"

VERSION = "01000000"
HASH_PREV_BLOCK = "81cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000"
HASH_MERKLE_ROOT = "e320b6c2fffc8d750423db8b1eb942ae710e951ed797f7affc8892b0f1fc122b"
TIME = "c7f5d74d"
TARGET = "f2b9441a"
NONCE = "42a14695"

HEADER_HEX = VERSION + HASH_PREV_BLOCK + HASH_MERKLE_ROOT + TIME + TARGET + NONCE

HASH = sha256(sha256(binascii.unhexlify(HEADER_HEX)).digest()).digest()
HASH_HEX = binascii.hexlify(HASH)

data = []

for i in range(0, len(HASH_HEX), 2):
    data.append(HASH_HEX[i:i+2].decode("utf-8"))
data.reverse()

print(''.join(data))
