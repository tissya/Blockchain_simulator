/*
シミュレータ本体のプログラム
*/

class Main{
    public static void main(String[] args){
        Blockchain bc = new Blockchain(500,15); //ノルマ100, ノード数5(仮置き)
        BlockDAG bdag = new BlockDAG(100,5); //ノルマ100, ノード数5(仮置き)
        Raft raft = new Raft(100,5); //ノルマ100, ノード数5(仮置き)

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
    }
}