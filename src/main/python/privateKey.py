#!/usr/bin/env python
import codecs
import hashlib
from random import randrange

import base58
import ecdsa

counter = 1
j = 1
i = 1
k = 1

i = int("29d", 16)
value1 = str(hex(i)[2:])
while True:
    k = 1
    # i=randrange(int("3f",16),int("3f",16))
    j = randrange(int("00000000000000", 16), int("ffffffffffffff", 16))
    while k < randrange(2 ** 16, 2 ** 20):
        value2 = str(hex(j)[2:]).zfill(14)
        line = "00000000000000000000000000000000000000000000000" + value1 + value2
        k = k + 1
        j = j + 1

        # Convert hex private key to bytes
        private_key = bytes.fromhex(line)

        # Derivation of the private key
        signing_key = ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1).verifying_key
        verifying_key = signing_key.to_string()
        public_key = bytes.fromhex("80") + verifying_key
        key_hex = verifying_key.hex()

        if (ord(bytearray.fromhex(key_hex[-2:])) % 2 == 0):
            public_key_compressed = "02" + key_hex[0:64]
        else:
            public_key_compressed = "03" + key_hex[0:64]

        # Hashes of public key
        sha256_1 = hashlib.sha256(codecs.decode(public_key_compressed, 'hex'))
        ripemd160 = hashlib.new("ripemd160")
        ripemd160.update(sha256_1.digest())

        # Adding prefix to identify Network
        hashed_public_key = bytes.fromhex("00") + ripemd160.digest()

        # Checksum calculation
        checksum_full = hashlib.sha256(hashlib.sha256(hashed_public_key).digest()).digest()
        checksum = checksum_full[:4]

        # Adding checksum to hashpubkey
        bin_addr = hashed_public_key + checksum

        # Encoding to address
        address = str(base58.b58encode(bin_addr))
        # final_address=address.decode('utf-8')
        final_address = address[2:-1]
        print(f"{line} {final_address}", end='\r')
        # print(final_address)
        if (final_address == "13zb1hQbWVsc2S7ZTZnP2G4undNNpdh5so"):
            # print(final_address)
            # print(line)
            file = open("Hacked.txt", "a")
            file.writelines("%s\n" % final_address)
            file.writelines("%s\n" % line)
            file.close()
            exit()
        if (final_address[0:7] == "13zb1hQ"):
            print(final_address, end="\r")
            file = open("test.txt", "a")
            file.writelines("%s\n" % final_address)
            file.writelines("%s\n" % line)
            file.close()
