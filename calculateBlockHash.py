import hashlib

header_hex = ("30000000" +  # version
              "0000000000000000000000000000000000000000000000000000000000000000" +  # hashPrevBlock
              "51f5de334085b92ce27c03888c726c9b2bb78069e55aeb6b236b03111580819a" +  # hashMerkleRoot
              "1f5dddf3" +  # Time
              "7af57690" +  # Target
              "63f055cd9a816794" +  # Nonce
              "6bfeb3c095be5da1442663985403867578")

header_bin = bytes.fromhex(header_hex)

hash = hashlib.sha256(hashlib.sha256(header_bin).digest()).digest()

print(hash.hex())
print(hash[::-1].hex())
