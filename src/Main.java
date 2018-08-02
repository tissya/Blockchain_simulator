/**
 * シミュレータ本体のプログラム
 */
class Main {
    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain(500, 15); // ノルマ100, ノード数5(仮置き)
        BlockDAG blockDAG = new BlockDAG(100, 5); // ノルマ100, ノード数5(仮置き)
        Raft raft = new Raft(100, 5); // ノルマ100, ノード数5(仮置き)

        blockChain.run(); // Blockchainのシミュレーション開始
        blockDAG.run(); // BlockDAGのシミュレーション開始
        raft.run(); // Raftのシミュレーション開始

        Integer bcMessage = blockChain.getMessageNum();
        Integer bcTurn = blockChain.getTurn();
        Integer raftMessage = raft.getMessageNum();
        Integer raftTurn = raft.getTurn();

        // Blockchainシミュレート結果を出力する
        System.out.println("BlockChain : Message = " + bcMessage + " Turn = " + bcTurn);
        System.out.println("Raft : Message = " + raftMessage + " Turn = " + raftTurn);
    }
}