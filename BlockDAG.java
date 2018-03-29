//ブロックの中身かぶってないかの検査をどうすっかなー
//検査はどのタイミングで行われるか調べる:(
import java.lang.Math;

class BlockDAG{
    private int norma; ////全てのノードにブロックが"norma"回追加するまでシミュレートする
    private int node; //ノード数
    private int consensus = 0;//合意されたブロック数
    private int messageNum = 0;//送信されたメッセージ数
    private int turn = 0;//経過ターン数
    private boolean mining = false;

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

        //自身以外にメッセージを送信する
        private void sendingMessage(){
            messageNum += nodes - 1;
        }

    public void run(){
        //BlockDAG用のノードを指定分作成する
        DagNode[] node = new DagNode[nodes]; 
        for(int i = 0; i < nodes; i++){
            node[i] = new DagNode();
        }

        //以下実行内容を記述する
        while(consensus < norma){
            if(mining){
                //ノードNo.0が発掘したものを前提として、他のノードにブロックを検査させるようにする
                if(!node[0].getInspectRun() && !node[1].getInspectRun()){//ノードNo.0がブロック検査をしていない時
                    Block block = new Block();
                    sendingMessage();//自身以外にメッセージを送信する
                    sendMessage = true;//発見したブロックを自身以外の全てのノードに通知する
                    node[0].inspect(block);//実際はすでにinspect済みである(マイニングした時点で)
                }

                //ノードNo.0がブロック検査をしていて、かつ他のノードがブロック検査していない時
                else if(node[0].getInspectRun() && !node[1].getInspectRun()){
                    node[0].addBlock();//No.0がブロックを追加
                    Block block = new Block();
                    //No.0以外の全てのノードがブロック検査をする
                    for(int i = 1; i < node.length;i++){
                        node[i].inspect(block);
                    }
                }

                //最後にNo.0以外のノード全てがブロックを追加する
                else{
                    for(int i = 1; i < node.length;i++){
                        node[i].addBlock();
                    }
                    mining = false;//全てのノードがブロックを追加した時、次のマイニングを開始する
                }
            }//if(mining)
            else{
                mining = true;
            }//else(mining)
            turn++;//全てのノードが1回処理する毎にターンを1増やす
            break;
        }//while
        return;
    }
}