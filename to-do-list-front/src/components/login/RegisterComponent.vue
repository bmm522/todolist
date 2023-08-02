<template>
    <q-card class="my-card bg-blue-1 text-black">
        <q-card-section>
            <div id="register-div" class="q-gutter-y-md column">
                <q-form @submit="register" @reset="onReset">
                    <h4 style="margin-right: 135px">📄회원가입📄</h4>
                    <br />
                    <div style="margin-right: 360px">아이디 (필수)</div>
                    <q-input
                        style="width: 300px"
                        filled
                        v-model="userId"
                        maxlength="12"
                        lazy-rules
                        :rules="[
                            (val) =>
                                val.length >= 4 ||
                                '최소 4자 ~ 최대 12자  입력하세요',
                        ]"
                    />

                    <br />

                    <q-btn
                        style="margin-right: 150px"
                        label="중복체크"
                        @click="checkDuplicateUserId"
                    />

                    <br />
                    <br />
                    <div style="margin-right: 340px">비밀번호 (필수)</div>
                    <q-input
                        style="width: 300px"
                        filled
                        type="password"
                        v-model="password"
                        maxlength="20"
                        lazy-rules
                        :rules="[
                            (val) =>
                                (val.length > 8 && passwordRegex.test(val)) ||
                                '영어, 숫자, 특수문자를 포함하여 최소 8자 ~ 최대 20자 입력하세요',
                        ]"
                    />
                    <br />
                    <div style="margin-right: 300px">
                        비밀번호 재입력 (필수)
                    </div>
                    <q-input
                        style="width: 300px"
                        filled
                        v-model="rePassword"
                        type="password"
                        maxlength="20"
                        lazy-rules
                        :rules="[
                            (val) => val === password || '일치하지 않습니다',
                        ]"
                    />

                    <br />
                    <div style="margin-right: 360px">닉네임 (필수)</div>
                    <q-input
                        style="width: 300px"
                        filled
                        v-model="nickname"
                        maxlength="8"
                        lazy-rules
                        :rules="[
                            (val) =>
                                val.length >= 2 ||
                                '최소 2자 ~ 최대 8자  입력하세요',
                        ]"
                    />

                    <div style="margin-right: 130px">
                        <q-btn
                            label="회원가입"
                            type="submit"
                            :disable="disableBtn"
                        />
                        <q-btn label="입력 초기화" type="reset" />
                        <router-link to="/login">
                            <q-btn label="로그인 페이지로" />
                        </router-link>
                    </div>
                </q-form>
            </div>
        </q-card-section>
    </q-card>
</template>

<script setup>
import { computed, ref, watch } from "vue";
import UserApi from "src/common/user/UserApi";
import { Notify, useQuasar } from "quasar";
// import router from "src/router/index";
import { createRouter as $router, useRoute, useRouter } from "vue-router";
import routes from "src/router/routes";
import { route } from "quasar/wrappers";
import router from "src/router";
import CommonNotify from "src/common/CommonNotify";

const userId = ref("");
const password = ref("");
const rePassword = ref("");
const nickname = ref("");
const emits = defineEmits("clickRegisterButton");

const isDuplicatedCheckUserId = ref(false);

const passwordRegex =
    /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;

const checkDuplicateUserId = async () => {
    if (userId.value.length === 0) {
        CommonNotify.fail("아이디를 입력하고 체크해주세요");
        isDuplicatedCheckUserId.value = false;
        return;
    }
    const data = await UserApi.checkDuplicateUsernameApi(userId.value);
    const duplicateCheckResult = data.body.duplicateCheckResult;

    if (duplicateCheckResult) {
        CommonNotify.fail("이미 사용중인 아이디입니다.");
        isDuplicatedCheckUserId.value = false;
    } else {
        CommonNotify.success("사용 가능한 아이디입니다.");
        isDuplicatedCheckUserId.value = true;
    }
};

const register = async () => {
    if (!isDuplicatedCheckUserId.value) {
        CommonNotify.fail("아이디 중복체크를 확인해주세요.");
    }
    const data = await UserApi.registerApi(
        userId.value,
        password.value,
        nickname.value
    );

    if (data.code === 201) {
        CommonNotify.success("회원가입이 완료되었습니다.");
        await router.push({ name: "login" });
    } else {
        CommonNotify.fail("서버에 문제가 생겼습니다.");
    }
};

const onReset = () => {
    userId.value = "";
    password.value = "";
    rePassword.value = "";
};

const disableBtn = computed(() => {
    return (
        userId.value.length === 0 ||
        password.value.length === 0 ||
        rePassword.value.length === 0 ||
        nickname.value.length === 0
    );
});
</script>

<style scoped>
.my-card {
    width: 100%;
    max-width: 600px;
    margin: auto;
    margin-top: 300px;
}

#register-div {
    text-align: center;
    margin-left: 120px;
}
</style>
