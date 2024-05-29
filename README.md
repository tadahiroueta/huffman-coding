# Huffman Coding
***A collection of compression algorithms that I replicated***

[Built With](#built-with) · [Features](#features) · [Installation](#installation) · [Usage](#usage)

## Built With

- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=oracle&logoColor=white)

## Features

### Huffman Coding
A weighted binary tree is used to encode and later decode some text - as a symmetric key - for lossless compression

```
               0
              / \
             /   \
            /     \
           /       \
          /         \
         /           \
        /             \
       /               \        
       0               0
      / \             / \       
     /   \           /   \      
    /     \         /     \     
   /       \       /       \    
   0       0       0       0       
  / \     / \     / \     / \   
 /   \   /   \   /   \   /   \  
 1   0   2   4   0   68   82   69   
    / \         / \
    65 38       66 256
```
> Huffman tree for ```"abracadabra"```
>
> ```TreePrinter.java``` is depricated because the tree becomes too large to read with large bodies of text

### Move to Front
A matrix is made to further compress the message, complementing the Huffman algorithm

```
bananaaa	1                 (abcdefghijklmnopqrstuvwxyz)
bananaaa	1,1               (bacdefghijklmnopqrstuvwxyz)
bananaaa	1,1,13            (abcdefghijklmnopqrstuvwxyz)
bananaaa	1,1,13,1          (nabcdefghijklmopqrstuvwxyz)
bananaaa	1,1,13,1,1        (anbcdefghijklmopqrstuvwxyz)
bananaaa	1,1,13,1,1,1      (nabcdefghijklmopqrstuvwxyz)
bananaaa	1,1,13,1,1,1,0    (anbcdefghijklmopqrstuvwxyz)
bananaaa	1,1,13,1,1,1,0,0  (anbcdefghijklmopqrstuvwxyz)
Final       1,1,13,1,1,1,0,0  (anbcdefghijklmopqrstuvwxyz)
```
> This algorithm is actually used to complement the Burrows-Wheeler algorithm, but I couldn't get the time complexity low enough to be practical. So I just used it together with Huffman

### Burrows Wheeler
Basically, rearranges sequences to increase the frequency of consecutive characters.

![wheely](https://cs.carleton.edu/cs_comps/2324/sequenceAlignment/layla-4.png)

> Unfortunately, I couldn't find the function I needed with Java's new String, so the time complexity got multiplied
>
> I separated this algorithm from the rest to avoid a bottle-neck

## Installation
<!-- Find more language syntax identifiers for code blocks here, https://github.com/jincheng9/markdown_supported_languages -->
1. Install specific programming language compiler.

    *Just a little note without non-crucial infomation*.
2. Clone repository
    ```sh
    git clone https://github.com/tadahiroueta/repository.git
    ```
3. Install dependencies
    ```sh
    npm install
    ```

## Usage

1. Pick an algorithm to test; type one of the functions in ```Runner.java```'s ```main(String[])``` in line 35
    > Choose between:
    > - ```testHuffman(String)```
    > - ```testMoveToFront(String)```
    > - ```testTwo(String)```
    > - ```testBurrowsWheeler(String)```

2. Add a text file as an argument to encrypt and decrypt
    > Choose between:
    > - ```abracadabra.txt```
    > - ```Hamlet.txt```
    > - ```happy hip hop.txt```
    > - ```short.txt```
    > - ```War and Peace.txt```
    ```java
    public static void main(String[] args) {
        testTwo("War and Peace.txt"); // huffman + move-to-front
    }
    ```
    > for example

3. Run ```Runner.java```

4. Encrypted and decrypted files will be created on your repository
    > Each function will produce different file types for both encryption and decryption
    >
    > Huffman:
    > - Encrypted - ```.short```
    > - Decrypted - ```.new```
    >
    > Move-to-Front:
    > - Encrypted - ```.front```
    > - Decrypted - ```.back```
    >
    > Burrows-Wheeler:
    > - Encrypted - ```.burrows```
    > - Decrypted - ```.wheeler```
