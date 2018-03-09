class RaftNode{
    private int term = 0; //Raftの論理時間
    private int commit = 0; //コミットされたデータ数
    private String role = "Follower"; //このノードの役割(Follower, Candidate, Leaderのいずれかになる)
    private int candidateTime = 0; //Candidateになるまでの論理時間。0になるとCandidateに。

    //コンストラクタ:何もなし
    RaftNode(){
    }

    //コンストラクタ:candidateになるまでの時間を指定する
    RaftNode(int time){
        this.candidateTime = time;
    }

    //コミットされたデータ数を返す
    public int getCommit(){
        return commit;
    }

    //役割を返す
    public String getRole(){
        return role;
    }

    //役割を設定するメソッド
    public void setRole(String choice){
        role = choice;
        return;
    }

    //candidateTimeを1減らすメソッド
    public String countdown(){
        this.candidateTime = this.candidateTime - 1;
        //candidateTimeから1減らした後0になった時、Candidateに
        if(this.candidateTime == 0){
            this.role = "Candidate";
            this.term = this.term + 1;//candidateになった時自身のtermを+1する
        } 
        return this.role; //このノードの役割を返す
    }

    //Termを1増やすメソッド
    public void countTerm(){
        term = term + 1;
    }


    //データがコミットされた時用のメソッド
    public void commitData(){
        commit++;//コミットされたデータ数を+1する
        return;
    }
}