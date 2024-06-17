{ pkgs? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.python3
    pkgs.python3.pkgs.pandas
    pkgs.python3.pkgs.scikit-learn
    pkgs.python3.pkgs.numpy
    pkgs.python3.pkgs.matplotlib
  ];
}
