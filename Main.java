/*
シミュレータ本体のプログラム
*/
import java.util.Scanner;

class Main{

    public static void main(String[] args){
        int nodeNum = 0; //全てのプロトコルシミュレートにおけるノード数を決定する
        int normaNum = 0; ////全てのプロトコルシミュレートにおける必要合意数を決定する

        //標準入力でノード数を決定する
        System.out.print("Input the number of node in this simulation > ");
        Scanner scan1 = new Scanner(System.in);
        nodeNum = scan1.nextInt();
        
        //標準入力で必要合意数を決定する
        System.out.print("Input the number of agreement required in this simulation > ");
        Scanner scan2 = new Scanner(System.in);
        normaNum = scan2.nextInt();

        Blockchain bc = new Blockchain(nodeNum, normaNum); //ノルマ100, ノード数5(仮置き)
        BlockDAG bdag = new BlockDAG(nodeNum, normaNum); //ノルマ100, ノード数5(仮置き)
        Raft raft = new Raft(nodeNum, normaNum); //ノルマ100, ノード数5(仮置き)

        //ノード数と必要合意数が共に1以上である時にシミュレートを実行する
        if(nodeNum >= 1 && normaNum >= 1){
            System.out.println("Simulation Start!!");
            bc.run();//Blockchainのシミュレーション開始
            //gdag.run();//BlockDAGのシミュレーション開始
            raft.run();//Raftのシミュレーション開始

            //Blockchainのメッセージ数とターン数を取得
            int bcMessage = bc.getMessageNum();
            int bcTurn = bc.getTurn();

            //raftのメッセージ数とターン数を取得
            int raftMessage = raft.getMessageNum();
            int raftTurn = raft.getTurn();

            //BlockDAGのメッセージ数とターン数を取得
            int bdagMessage = bdag.getMessageNum();
            int bdagTurn = bdag.getTurn();

            //Blockchain,raft,BlockDAGのシミュレート結果を出力する
            System.out.println("Blockchain : Message = " + bcMessage + " Turn = " + bcTurn);
            System.out.println("Raft : Message = " + raftMessage + " Turn = " + raftTurn);
            System.out.println("BlockDAG : Message = " + bdagMessage + " Turn = " + bdagTurn);
        }//if(nodeNum >= 1 && norma >= 1)

        //パラメータがおかしい場合はエラー文を出す
        else{ System.out.println("Error!! : The number entered is wrong."); }
    }
}