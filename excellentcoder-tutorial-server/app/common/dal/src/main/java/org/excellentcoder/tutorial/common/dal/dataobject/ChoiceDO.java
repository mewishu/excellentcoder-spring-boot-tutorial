package org.excellentcoder.tutorial.common.dal.dataobject;

public class ChoiceDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column choices.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column choices.text
     *
     * @mbg.generated
     */
    private String text;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column choices.poll_id
     *
     * @mbg.generated
     */
    private Long pollId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column choices.id
     *
     * @return the value of choices.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column choices.id
     *
     * @param id the value for choices.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column choices.text
     *
     * @return the value of choices.text
     *
     * @mbg.generated
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column choices.text
     *
     * @param text the value for choices.text
     *
     * @mbg.generated
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column choices.poll_id
     *
     * @return the value of choices.poll_id
     *
     * @mbg.generated
     */
    public Long getPollId() {
        return pollId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column choices.poll_id
     *
     * @param pollId the value for choices.poll_id
     *
     * @mbg.generated
     */
    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table choices
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChoiceDO other = (ChoiceDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getPollId() == null ? other.getPollId() == null : this.getPollId().equals(other.getPollId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table choices
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getPollId() == null) ? 0 : getPollId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table choices
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", text=").append(text);
        sb.append(", pollId=").append(pollId);
        sb.append("]");
        return sb.toString();
    }
}