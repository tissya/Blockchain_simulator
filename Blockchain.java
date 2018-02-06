/*
Blockchainのプログラム
前提として
1,マイニングを成功させるのは必ずnode[0]である
2,node[0]以外は承認した後に承認メッセージをメッセージを送信しない(承認要求メッセージに対して返信しない)
3,チェーンは分岐しない
4,ノードは故障せずネットワークから離脱しない、また悪意のあるノードは存在しない
5,承認要求されたブロックが拒否されることはない(必ずチェーンに追加される)

また、このプログラムの性質として
1,コンセンサスの数は各々のノードのチェーンに入っているブロック数を比較して最小の値を採用する

このプログラムの問題点
1,承認メッセージに返信しないのはまずい・・・？
*/

import java.lang.Math;

class Blockchain{
    int consensus = 0; //全てのノードにブロックが追加された数
    int norma; //全てのノードにブロックが"norma"回追加するまでシミュレートする
    int nodes; //ノード数
    int turn = 0;//経過ターン数
    int messageNum = 0;//メッセージ数
    boolean mining = false;//マイニングのフラグ
    boolean sendMessage = false;//マイニングしたブロック通知の管理フラグ

    //コンストラクタ
    Blockchain(int norma,int node){
        this.norma = norma;
        this.nodes = node;
    }
    Blockchain(){
        //エラー
    }

    public int getMessageNum(){
        return messageNum;
    }

    public int getTurn(){
        return turn;
    }

    public void run(){
        Node[] node = new Node[nodes]; //指定分のノードを作成

        while(consensus < norma){//指定ブロック数のコンセンサスが取れるまでループし続ける
            if(mining){//マイニングされた後の処理
                //ノードNo.0が発掘したものを前提として、他のノードにブロックを検査させるようにする
                    if(!node[0].getInspectRun() && !node[1].getInspectRun()){//ノードNo.0がブロック検査をしていない時
                        Block block = new Block();
                        messageNum += nodes - 1;//自身以外にメッセージを送信する
                        sendMessage = true;//発見したブロックを自身以外の全てのノードに通知する
                        node[0].inspect(block);//実際はすでにinspect済みである(マイニングした時点で)
                    }

                    else if(node[0].getInspectRun() && !node[1].getInspectRun()){//ノードNo.0がブロック検査をしていて、かつ他のノードがブロック検査していない時
                        node[0].addBlock();//No.0がブロックを追加
                        Block block = new Block();
                        //No.0以外の全てのノードがブロック検査をする
                        for(int i = 1; i < node.length;i++){
                            node[i].inspect(block);
                        }
                    }

                    else{//最後にNo.0以外のノード全てがブロックを追加する
                        for(int i = 1; i < node.length;i++){
                            node[i].addBlock();
                        }
                        mining = false;//全てのノードがブロックを追加した時、次のマイニングを開始する
                    }
            }//if(mining)

            else{//マイニングされていない時全てのノードはマイニングをする
                mining = true;
            }

            turn++;//全てのノードが1回処理する毎にターンを1増やす
            //全てのノードのブロックチェーンに含まれるブロック数を比較してどれだけブロックが追加(コンセンサス)されているか求める
            consensus = node[0].getChainlength();
            for(int i = 1; i < node.length;i++){
                int length = node[i].getChainlength();
                consensus = Math.min(consensus, length);//追加されたブロック数を比較して最小の値をコンセンサスされた数として扱う
            }
        }//while
    }
}