<template>
    <q-page-container>
        <q-card class="my-card">
            <q-card-section>
                <div
                    id="login-div"
                    class="q-gutter-y-md column"
                    style="max-width: 350px"
                >
                    <h4>⚙️닉네임 변경하기⚙️</h4>
                    <br />
                    새로운 닉네임
                    <q-input
                        filled
                        v-model="nickname"
                        maxlength="8"
                        label="닉네임을 입력하세요"
                        lazy-rules
                        :rules="[
                            (val) =>
                                val.length >= 2 ||
                                '최소 2자 ~ 최대 8자  입력하세요',
                        ]"
                    />
                    <br />
                    <div style="float: left">
                        <q-btn
                            :disable="disableBtn"
                            style="width: 107px"
                            outline
                            color="black"
                            label="닉네임 변경"
                            @click="editNickname"
                        />
                    </div>
                    <div></div>
                </div>
            </q-card-section>
        </q-card>
    </q-page-container>
</template>

<script setup>
import { computed, ref } from "vue";
import UserApi from "src/common/user/UserApi";
import { useStore } from "stores/store";
import CommonNotify from "src/common/CommonNotify";

const nickname = ref("");
const store = useStore;
const editNickname = async () => {
    if (nickname.value.length < 2) {
        CommonNotify.fail("닉네임을 제대로 작성해주세요");
        return;
    }
    const data = await UserApi.editUserNickname(nickname.value);
    store.userInfoData.data.nickname = data.body.updatedNickname;
};

const disableBtn = computed(() => {
    return nickname.value.length < 2;
});
</script>

<style scoped>
.my-card {
    width: 100%;
    max-width: 600px;
    margin: auto;
    margin-top: 300px;
}

#login-div {
    text-align: center;
    margin-left: 110px;
}
</style>
