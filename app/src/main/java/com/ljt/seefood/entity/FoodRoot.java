package com.ljt.seefood.entity;

import java.util.List;

/**
 * Created by 1 on 2017/3/7.
 */

public class FoodRoot {
    private String resultcode;

    private String reason;

    private Result result;

    private int error_code;

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultcode() {
        return this.resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return this.result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return this.error_code;
    }

    public class Result {
        private List<Data> data;

        private String totalNum;

        private String pn;

        private String rn;

        public void setData(List<Data> data) {
            this.data = data;
        }

        public List<Data> getData() {
            return this.data;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getTotalNum() {
            return this.totalNum;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public String getPn() {
            return this.pn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public String getRn() {
            return this.rn;
        }

        public class Data {
            private String id;

            private String title;

            private String tags;

            private String imtro;

            private String ingredients;

            private String burden;

            private List<String> albums;

            private List<Steps> steps;

            public void setId(String id) {
                this.id = id;
            }

            public String getId() {
                return this.id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getTags() {
                return this.tags;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getImtro() {
                return this.imtro;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getIngredients() {
                return this.ingredients;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public String getBurden() {
                return this.burden;
            }

            public void setString(List<String> albums) {
                this.albums = albums;
            }

            public List<String> getString() {
                return this.albums;
            }

            public void setSteps(List<Steps> steps) {
                this.steps = steps;
            }

            public List<Steps> getSteps() {
                return this.steps;
            }

            public class Steps {
                private String img;

                private String step;

                public void setImg(String img) {
                    this.img = img;
                }

                public String getImg() {
                    return this.img;
                }

                public void setStep(String step) {
                    this.step = step;
                }

                public String getStep() {
                    return this.step;
                }
            }
        }


    }
}