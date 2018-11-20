# PLA用論理式作成ツール

7の配線を持つPLAに対して，ある真理値表に従って配線するための主加法標準形の論理式を自動作成するツールです．

## Installation
Kotlinをインストールします．

GitHubからリポジトリをコピーしたら，以下のようにしてビルドを行います．

```sh
cd PLA
kotlinc -d pla.jar -include-runtime pla.kt
```

## Usage
`plafileQ`というファイルにQの真理値表を記入し，同様にして，`plafileD`というファイルにDの真理値表を記入します．

この真理値表は，TeXの表の形式で入力します．

例）
```
0 & 0 & 0 & 0 & 0 & 0 & 0
1 & 1 & 0 & 1 & 1 & 1 & 1
1 & 1 & 1 & 1 & 1 & 1 & 1
1 & 1 & 1 & 0 & 0 & 1 & 0
1 & 1 & 1 & 1 & 1 & 1 & 0
1 & 1 & 1 & 0 & 0 & 1 & 1
1 & 1 & 0 & 0 & 0 & 1 & 1
```

入力が終わったら（なお，このリポジトリに同梱されてるファイルも参考にしてください．），以下のようにして実行するだけです．

```sh
java -jar pla.kt
```

実行すると，標準出力にTeXの数式の形式で主加法標準形の論理式が出力されます．
あとは，これをコピペするだけで使えますが，簡単化は全く行わないので，適宜簡単化を行い，レポートを提出します．
なお，`eqnarray`環境にコピペするだけで適宜改行や行番号を出力します．体裁のことは全く考えずに作業ができます．

## Features
* すべて純粋関数で記述されています．
* TeXの数式の形式で出力する際，それなりに整形します．
* WindowsとLinuxで動作確認済みです（Kotlincは1.3.0，Javaは1.8.0_192を使用）