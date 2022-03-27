# RSA CLI

This is an oversimplified implementation of RSA encryption. This tool allows for simple encryption/decryption from a
file using a generated RSA keypair.

This tool should not be used and is simply an implementation to gain better understanding on how RSA works.
Each character of the input file is encrypted separately which makes this highly vulnerable to very simple attacks.

## Keypair generation
To generate a new keypair use the following command.
```
generate -o <OUTPUT_DIR>
```

## Encrypting a file

The encrypt command can be used to encrypt a given file (`-i`) using a public key (`-p`).
The encrypted data will be written to a file `chiffre.txt` in the specified output (`-o`) directory.

```
encrypt -p <PUBLIC_KEY> -o <OUTPUT_DIR_PATH> -i <INPUT_FILE_PATH>
```

## Decrypting a file

To decrypt a file use the decrypt command by passing it the private key (`-s`) the path to the encrypted file (`-i`)
and the output directory (`-o`). The decrypted data will be written to a file `text-d.txt` in the specified output directory.

```
decrypt -s <PRIVATE_KEY> -o <OUTPUT_DIR_PATH> -i <INPUT_FILE_PATH>   decrypt a given file
```