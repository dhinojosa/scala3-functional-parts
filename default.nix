{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.scala_3
    pkgs.sbt
  ];

  shellHook = ''
    echo "Scala 3 and the latest SBT environment ready."
  '';
}

