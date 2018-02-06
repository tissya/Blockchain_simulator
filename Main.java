class Main{
    public static void main(String[] args){
        Blockchain bc = new Blockchain(100,5); //ノルマ100, ノード数5(仮置き)
        BlockDAG bdg = new BlockDAG(100,5); //ノルマ100, ノード数5(仮置き)

        bc.run();//ブロックチェーンのシミュレーション開始

        int Bc_message = bc.getMessageNum();
        int Bc_turn = bc.getTurn();

        //Blockchainシミュレート結果を出力する
        System.out.println("Blockchain : Message = " + Bc_message + " Turn = " + Bc_turn);
    }
}