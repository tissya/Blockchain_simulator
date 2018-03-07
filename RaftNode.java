class RaftNode{
    private int term = 0; //Raftの論理時間
    private int commit = 0;//コミットされたデータ数
    private String role = "Candidate"; //このノードの役割(Follower, Candidate, Leaderのいずれかになる)

    //コミットされたデータ数を返す
    public int getCommit(){
        return commit;
    }

    //役割を設定するメソッド
    public void setRole(String choice){
        role = choice;
        return;
    }

    //データがコミットされた時用のメソッド
    public void commitData(){
        commit++;//コミットされたデータ数を+1する
        return;
    }
}