import java.lang.Math;

class BlockDAG{
    private int norma; ////全てのノードにブロックが"norma"回追加するまでシミュレートする
    private int node; //ノード数
    private int consensus = 0;//合意されたブロック数
    private int messageNum = 0;//送信されたメッセージ数
    private int turn = 0;//経過ターン数

    //コンストラクタ
    BlockDAG(int norma,int node){
        this.norma = norma;
        this.node = node;
    }

    BlockDAG(){
        //エラー
    }

    public int getMessageNum(){
        return messageNum;
    }

    public int getTurn(){
        return turn;
    }

    public void run(){
        //実行内容を記述する
        while(true){
            break;
        }
        return;
    }
}