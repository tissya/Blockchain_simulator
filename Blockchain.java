/**
Blockchainのプログラム
前提として
1,マイニングを成功させるのは必ずnode[0]である
2,node[0]以外は承認した後に承認メッセージをメッセージを送信しない(承認要求メッセージに対して返信しない)
3,チェーンは分岐しない
4,ノードは故障せずネットワークから離脱しない、また悪意のあるノードは存在しない
5,承認要求されたブロックが拒否されることはない(必ずチェーンに追加される)

また、このプログラムの性質として
1,コンセンサスの数は各々のノードのチェーンに入っているブロック数を比較して最小の値を採用する
2,配信者のノードはランダムであるが、必ず各ノードに配信の機会は訪れる

このプログラムの問題点
1,承認メッセージに返信しないのはまずい・・・？
*/

import java.lang.Math;

class Blockchain{
    /**
     * 全てのノードにブロックが追加された数
     */
    int consensus = 0;

    /**
     * 全てのノードにブロックが"norma"回追加するまでシミュレートする
     */
    int norma;

    /**
     * ノード数
     */
    int nodes;

    /**
     * 経過したターン数
     */
    int turn = 0;

    /**
     * メッセージ数
     */
    int messageNum = 0;

    /**
     * マイニングのフラグ
     */
    boolean isMining = false;

    /**
     * 配信者以外のノードがブロックを検査をしたかどうかを判定する
     */
    boolean anotherInspect = false;//配信者以外のノードがブロックを検査したどうかのフラグ

    //コンストラクタ(エラー処理はsetterのExceptionを投げる)
    Blockchain(int node,int norma){
        this.norma = norma;
        this.nodes = node;
    }

    public int getMessageNum(){
        return messageNum;
    }

    public int getTurn(){
        return turn;
    }

    //自身以外にメッセージを送信する
    private void sendingMessage(){
        messageNum += nodes - 1;
    }

    public void run(){
        //指定分のノードを作成
        BcNode[] node = new BcNode[nodes]; 
        //配信ノードを決定するためのパラメータ
        int distributor = 0;//配信者のノード番号
        PoW p = new PoW(nodes); //PoWを行うためのインスタンスを作成する

        
        for(int i = 0; i < nodes; i++){
            node[i] = new BcNode();
        }
        
        while(consensus < norma){//指定ブロック数のコンセンサスが取れるまでループし続ける
            if(isMining){//マイニングされた後の処理
                //ノードNo.0が発掘したものを前提として、他のノードにブロックを検査させるようにする
                    if(!node[distributor].getInspectRun() && !anotherInspect){//ノードNo.0がブロック検査をしていない時
                        Block block = new Block();
                        sendingMessage();//自身以外にメッセージを送信する
                        node[distributor].inspect(block);//実際はすでにinspect済みである(マイニングした時点で)
                    }

                    else if(node[distributor].getInspectRun() && !anotherInspect){//ノードNo.0がブロック検査をしていて、かつ他のノードがブロック検査していない時
                        node[distributor].addBlock();//No.0がブロックを追加
                        Block block = new Block();
                        //配信者以外の全てのノードがブロック検査をする
                        for(int i = 0; i < nodes;i++){
                            if(i != distributor) node[i].inspect(block);
                        }
                        anotherInspect = true;
                    }

                    else{//最後に配信者以外のノード全てがブロックを追加する
                        for(int i = 0; i < nodes;i++){
                            if(i != distributor) node[i].addBlock();
                        }
                        isMining = false;//全てのノードがブロックを追加した時、次のマイニングを開始する
                        anotherInspect = false;
                    }
            }//if(isMining)

            else{//マイニングされていない時、全てのノードはマイニングをする
                //配信者ノードをランダムで決定する
                 distributor = p.pow();

                isMining = true;
            }

            turn++;//全てのノードが1回処理する毎にターンを1増やす
            //全てのノードのブロックチェーンに含まれるブロック数を比較してどれだけブロックが追加(コンセンサス)されているか求める
            consensus = node[0].getChainlength();
            for(int i = 1; i < node.length;i++){
                int length = node[i].getChainlength();
                consensus = Math.min(consensus, length);//追加されたブロック数を比較して最小の値をコンセンサスされた数として扱う
            }
        }//while
        return;
    }
}