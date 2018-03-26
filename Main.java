/*
シミュレータ本体のプログラム
*/

class Main{
    public static void main(String[] args){
        Blockchain bc = new Blockchain(500,15); //ノルマ100, ノード数5(仮置き)
        BlockDAG bdg = new BlockDAG(100,5); //ノルマ100, ノード数5(仮置き)
        Raft raft = new Raft(100,5); //ノルマ100, ノード数5(仮置き)

        bc.run();//Blockchainのシミュレーション開始
        //gdg.run();//BlockDAGのシミュレーション開始
        raft.run();//Raftのシミュレーション開始

        int bcMessage = bc.getMessageNum();
        int bcTurn = bc.getTurn();
        int raftMessage = raft.getMessageNum();
        int raftTurn = raft.getTurn();

        //Blockchainシミュレート結果を出力する
        System.out.println("Blockchain : Message = " + bcMessage + " Turn = " + bcTurn);
        System.out.println("Raft : Message = " + raftMessage + " Turn = " + raftTurn);
    }
}