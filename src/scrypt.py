import hashlib
header_hex = ("version" +
              " hashPrevBlock" +
              " hashMerkleRoot " +
              " Time " +
              "Target" +
              "Nonce")
header_bin = header_hex.decode('hex')
hash = hashlib.sha256(hashlib.sha256(header_bin).digest()).digest()
print hash.encode('hex_codec')
print hash[::-1].encode('hex_codec')