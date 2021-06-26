                                                                         ▄▄▄     ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄    ▄▄▄▄▄▄▄ ▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄   ▄▄ ▄▄▄▄▄▄   ▄▄▄▄▄▄▄ 
                                                                        █   █   █       █       █  █  ▄    █      █       █   █       █       █       █  █ █  █   ▄  █ █       █
                                                                        █   █   █    ▄▄▄█  ▄▄▄▄▄█  █ █▄█   █  ▄   █▄     ▄█   █  ▄▄▄▄▄█  ▄▄▄▄▄█    ▄▄▄█  █ █  █  █ █ █ █  ▄▄▄▄▄█
                                                                        █   █   █   █▄▄▄█ █▄▄▄▄▄   █       █ █▄█  █ █   █ █   █ █▄▄▄▄▄█ █▄▄▄▄▄█   █▄▄▄█  █▄█  █   █▄▄█▄█ █▄▄▄▄▄ 
                                                                        █   █▄▄▄█    ▄▄▄█▄▄▄▄▄  █  █  ▄   ██      █ █   █ █   █▄▄▄▄▄  █▄▄▄▄▄  █    ▄▄▄█       █    ▄▄  █▄▄▄▄▄  █
                                                                        █       █   █▄▄▄ ▄▄▄▄▄█ █  █ █▄█   █  ▄   █ █   █ █   █▄▄▄▄▄█ █▄▄▄▄▄█ █   █▄▄▄█       █   █  █ █▄▄▄▄▄█ █
                                                                        █▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█  █▄▄▄▄▄▄▄█▄█ █▄▄█ █▄▄▄█ █▄▄▄█▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄▄▄█  █▄█▄▄▄▄▄▄▄█




                                                                                                ************************************

                                                                                                       Lancement du l'archive

                                                                                                ************************************

Quelque soit votre système d'exploitation, il existe deux façons de lancer l'archive .jar :

- En effectuant un clic droit sur l'archive --> lancer avec Java

- Par ligne de commande, en ouvrant le dossier courant contenant l'archive dans un terminal (bash, powershell) et effectuez la commande : java -jar GameLauncher-1.0.jar


ATTENTION ! Pensez bien à ne pas bouger l'archive de son dossier d'origine, l'interface graphique pourrait ne plus fonctionner en fonction de l'emplacement.
DE MEME POUR LE FICHIER build.xml !





                                                                                                ************************************

                                                                                                          Exécution de ANT

                                                                                                ************************************

Si vous souhaitez lancer les différents tests associés au code source du jeu, il faut plusieurs pré-requis.

- Il faut avoir installé et configuré les archives jar junit & harmcrest
- Il faut avoir configuré Ant et les variables d'environnement associées, à savoir ANT_HOME, JAVA_HOME, PATH
- Se placer dans le dossier ws contenant le fichier build.xml
- Lancer la commande : ant
