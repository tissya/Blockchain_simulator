class RaftNode {

    /**
     * Raftの論理時間
     */
    private Integer term = 0;

    /**
     * コミットされたデータ数
     */
    private Integer commit = 0;

    /**
     * このノードの役割(Follower, Candidate, Leaderのいずれかになる)
     */
    private NodeRole role = NodeRole.Follower;

    /**
     * Candidateになるまでの論理時間。0になるとCandidateに
     */
    private Integer candidateTime = 0;

    /**
     * リクエストを受け取ったかどうか判定(Leaderの場合は自発した時にtrueに)
     */
    private Boolean isRequested = false;

    /**
     * Constructor：candidateになるまでの時間を指定する
     */
    RaftNode(Integer time) {
        setCandidateTime(time);
    }

    /**
     * @return get term.
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * @param term set term.
     */
    public void setTerm(Integer term) {
        this.term = term;
    }

    /**
     * @return get commit.
     */
    public Integer getCommit() {
        return commit;
    }

    /**
     * @param commit set commit.
     */
    public void setCommit(Integer commit) {
        this.commit = commit;
    }

    /**
     * @return get role.
     */
    public NodeRole getRole() {
        return role;
    }

    /**
     * @param role set role.
     */
    public void setRole(NodeRole role) {
        this.role = role;
    }

    /**
     * @return get candidateTime.
     */
    public Integer getCandidateTime() {
        return candidateTime;
    }

    /**
     * @param candidateTime set candidateTime.
     */
    public void setCandidateTime(Integer candidateTime) {
        this.candidateTime = candidateTime;
    }

    /**
     * @return get isRequested.
     */
    public Boolean isRequested() {
        return isRequested;
    }

    /**
     * リクエストを受け取ったor自発した時にrequestスイッチをONにする
     *
     * @param isRequested set isRequested.
     */
    public void setRequested(Boolean requested) {
        isRequested = requested;
    }

    /**
     * candidateTimeを1減らすメソッド
     *
     * @return このノードの役割を返す
     */
    public NodeRole countDown() {
        Integer tempCandidateTime = getCandidateTime();
        setCandidateTime(tempCandidateTime - 1);

        if (tempCandidateTime == 0) { // candidateTimeから1減らした後0になった時、Candidateに
            setRole(NodeRole.Candidate);
            setTerm(getTerm() + 1); // candidateになった時自身のtermを+1する
        }

        return getRole();
    }

    /**
     * Termを1増やすメソッド
     */
    public void countTerm() {
        setTerm(getTerm() + 1);
    }

    /**
     * データがコミットされた時用のメソッド
     */
    public void commitData() {
        setCommit(getCommit() + 1); // コミットされたデータ数を+1する
        setRequested(false); // リクエストスイッチをfalseにする
    }
}