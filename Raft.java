/*
Raftのプログラム
・前提
1,各ノードは故障しない
2,最初は各ノードは役割を持っていないものとする(リーダー選出から始まる)
*/

class Raft{
    private int commit = 0; //どれだけコミットされたかカウントする
    private int norma; //全てのノードにブロックが"norma"回追加するまでシミュレートする
    private int nodes; //ノード数
    private int turn = 0;//経過ターン数
    private int messageNum = 0;//メッセージ数
    private boolean candidateSwitch = false; //ノード群の中にCandidateがいるかどうか(LeaderElection)
    private boolean leaderSwitch = false;//ノード群の中にLeaderがいるかどうか(LeaderElection)

    //コンストラクタ
    Raft(int norma,int node){
        this.norma = norma;
        this.nodes = node;
    }
    Raft(){
        //エラー
    }

    public int getMessageNum(){
        return messageNum;
    }

    public int getTurn(){
        return turn;
    }

    public void run(){
        //ノードを指定分作成する
        RaftNode[] node = new RaftNode[nodes]; 
        for(int i = 0; i < nodes; i++){
            node[i] = new RaftNode(i + 1);//candidateになるまでの時間を設定する
        }

        while(commit < norma){
            //全員がFollowerの場合(初期値)
            if(!candidateSwitch && !leaderSwitch){
                for(int i = 0; i < nodes; i++){
                    String role = node[i].countdown();//各ノードのcandidateTimeを1減らす
                    if(role == "Candidate") candidateSwitch = true;//ノードの中にCandidateがいたらcandidateSwitchをTrueに
                }
            }//if(!candidateSwitch && !leaderSwitch)

            //Candidateは存在するがLeaderがいない時(LeaderElection)
            else if(candidateSwitch && !leaderSwitch){
                int leader = 0; //どのノード番号がリーダになるかの変数
                for(leader = 0; leader < nodes; leader++){
                    if(node[leader].getRole() == "Candidate") break; //Candidateのノードが見つかったらbreak;する
                }
                node[leader].setRole("Leader");//breakしたときのノード番号のノードがLeaderになる

                //それ以外が全員Followerになる
                for(int i = 0; i < nodes; i++){
                    if(i != leader)node[i].setRole("Follower");
                }
                candidateSwitch = false;//candidateがいなくなるのでfalseに
                leaderSwitch = true;//リーダが選出されたのでtrueに
            }//else if(candidateSwitch && !leaderSwitch)(LeaderElection)

            else{
                //ここからリクエスト〜コミットの処理を記述する
            }//else

            turn++;//1ターンカウント
        }
        return;
    }
}