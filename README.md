# BabyHash

Ce programme est une application Spring Boot qui implémente un petit système de hachage (hashing). Le programme utilise la bibliothèque MessageDigest pour calculer la somme de contrôle SHA-1 des entrées utilisateur.

Le programme offre un menu avec trois options:
```
La première option permet à l'utilisateur de saisir des données pour générer un petit hachage, qui est ensuite utilisé pour l'extraction de blocs.
La deuxième option appelle un script Python externe, qui est utilisé pour calculer la somme de contrôle d'un bloc de données.
La troisième option permet à l'utilisateur de quitter le programme.
```
La fonction ComputeSHA_256_as_Hex_String() calcule la somme de contrôle SHA-1 pour une chaîne de caractères donnée, en utilisant l'algorithme SHA-1 fourni par MessageDigest. 

La fonction convertToHex() convertit le résultat de la somme de contrôle en une chaîne hexadécimale pour faciliter la lecture et l'affichage.

Le programme utilise également des classes Java standard telles que BufferedReader, InputStreamReader et ProcessBuilder pour lire l'entrée utilisateur, communiquer avec le script Python externe et afficher les résultats à l'utilisateur

Menu BabyHash

![img.png](IdeaProjects/BabyHash/src/image/img.png)

Option [1]

![img_1.png](IdeaProjects/BabyHash/src/image/img_1.png)

Option [2]

![img_2.png](IdeaProjects/BabyHash/src/image/img_2.png)


Le programme BabyHashApplicationSwing est une application Java qui utilise la bibliothèque Swing pour créer une interface graphique utilisateur (GUI).
L'application permet à l'utilisateur de générer un hachage SHA-256 à partir d'une chaîne de caractères en entrée, de calculer un hachage de bloc en appelant un script Python et de quitter l'application.

L'interface utilisateur est créée à l'aide de la classe JFrame et contient trois boutons: "Generate hash" pour générer un hachage à partir d'une chaîne de caractères, "Calculate block hash" pour calculer un hachage de bloc en appelant un script Python, et "Exit" pour quitter l'application.
Lorsque l'utilisateur clique sur le bouton "Generate hash", une boîte de dialogue s'ouvre pour lui demander d'entrer une chaîne de caractères. 

L'application concatène ensuite un nombre incrémental à la chaîne de caractères d'entrée jusqu'à ce que le hachage commence par quatre zéros. 
Le nombre d'itérations nécessaires pour obtenir le hachage avec quatre zéros est ensuite affiché, ainsi que le temps nécessaire pour le calcul. 


Le résultat est affiché dans une boîte de dialogue avec un JTextArea.
Lorsque l'utilisateur clique sur le bouton "Calculate block hash", l'application appelle un script Python pour calculer un hachage de bloc. 
Le résultat est affiché dans une boîte de dialogue avec un JTextArea.

Enfin, lorsque l'utilisateur clique sur le bouton "Exit", l'application se ferme.

Menu BabyHash

![img_3.png](IdeaProjects/BabyHash/src/image/img_3.png)

Option [1]

![img_4.png](IdeaProjects/BabyHash/src/image/img_4.png)

![img_5.png](IdeaProjects/BabyHash/src/image/img_5.png)

Option [2]

![img_6.png](IdeaProjects/BabyHash/src/image/img_6.png)