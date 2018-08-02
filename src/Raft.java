/*
Raftのプログラム
・前提
1,各ノードは故障しない
2,最初は各ノードは役割を持っていないものとする(リーダー選出から始まる)
・問題点
1,ハートビートをどうしよう??
*/

import java.lang.Math;

class Raft{
    private int commit = 0; //どれだけコミットされたかカウントする
    private int norma; //全てのノードにブロックが"norma"回追加するまでシミュレートする
    private int nodes; //ノード数
    private int turn = 0;//経過ターン数
    private int messageNum = 0;//メッセージ数
    private boolean candidateSwitch = false; //ノード群の中にCandidateがいるかどうか(LeaderElection)
    private boolean leaderSwitch = false;//ノード群の中にLeaderがいるかどうか
    private int leader = 0; //どのノード番号がリーダになるかの変数

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
            node[i].countTerm();//各ノードのtermを1にしておく
        }

        //以下Raftの仕様
        while(commit < norma){
            //全員がFollowerの場合(初期値)
            if(!candidateSwitch && !leaderSwitch){
                for(int i = 0; i < nodes; i++){
                    NodeRole role = node[i].countDown();//各ノードのcandidateTimeを1減らす
                    if(role == NodeRole.Candidate) candidateSwitch = true;//ノードの中にCandidateがいたらcandidateSwitchをTrueに
                }
            }//if(!candidateSwitch && !leaderSwitch)

            //Candidateは存在するがLeaderがいない時(LeaderElection)
            else if(candidateSwitch && !leaderSwitch){
                for(leader = 0; leader < nodes; leader++){
                    if(node[leader].getRole() == NodeRole.Candidate) break; //Candidateのノードが見つかったらbreak;する
                }
                node[leader].setRole(NodeRole.Leader);//breakしたときのノード番号のノードがLeaderになる
                messageNum += (nodes - 1) * 2; //Candidate -> 全体ノード数 - 1 にメッセージを送信する

                //それ以外が全員Followerになる
                for(int i = 0; i < nodes; i++){
                    if(i != leader){
                        node[i].setRole(NodeRole.Follower);
                        node[i].countTerm();//termを1増やす
                        messageNum++;//Leaderに返信する
                    }
                }
                candidateSwitch = false;//candidateがいなくなるのでfalseに
                leaderSwitch = true;//リーダが選出されたのでtrueに
            }//else if(candidateSwitch && !leaderSwitch)(LeaderElection)

            //ここからリクエスト〜コミットの処理
            else{
                //リーダがリクエストを自発していない場合
                if(!node[leader].isRequested() && !node[leader+1].isRequested()){
                    node[leader].setRequested(true); //リーダのリクエストを生成する
                }

                //リーダがリクエストを生成済み&Followerがリクエストを受け取っていない場合
                else if(node[leader].isRequested() && !node[leader+1].isRequested()){
                    for(int i = 0; i < nodes; i++){
                        if(i != leader){
                            node[i].setRequested(true);//Leader以外のノード全てがリクエストを受け取る
                            messageNum++; //Followerがリクエストメッセージを受信する(Leaderがリクエストメッセージを送信する)
                        }
                    }
                }

                //リーダがリクエストを生成済み&Followerがリクエストを受け取っている場合
                else if(node[leader].isRequested() && node[leader+1].isRequested()){
                    node[leader].commitData();//最初にLeader側でコミットする
                    messageNum += nodes - 1;//Followerのリクエストメッセージに対する返答の受信
                }

                //リーダがコミット済み&Followerがまだコミットしていない場合
                else{
                    for(int i = 0; i < nodes; i++){
                        if(i != leader){
                            node[i].commitData();//Leader以外のノード全てがリクエストを受け取る
                            messageNum++;//実際はここでハートビート(リクエスト内容のコミット確定)を送信する必要がある
                        }
                    }
                }

            }//else

            turn++;//1ターンカウント
            commit = node[0].getCommit();
            for(int i = 1; i < node.length;i++){
                int length = node[i].getCommit();
                commit = Math.min(commit, length);//追加されたブロック数を比較して最小の値をコンセンサスされた数として扱う
            }
        }//while
        return;
    }
}