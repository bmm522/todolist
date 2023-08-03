<template>
    <q-card class="my-card bg-blue-1 text-black">
        <q-card-section>
            <div
                id="login-div"
                class="q-gutter-y-md column"
                style="max-width: 300px"
            >
                <h4>📝TodoList 로그인</h4>
                <br />
                아이디
                <q-input
                    filled
                    color="teal-10"
                    type="text"
                    v-model="userId"
                    @keyup.enter.exact="login"
                />
                <br />
                비밀번호
                <q-input
                    filled
                    color="teal-10"
                    type="password"
                    v-model="password"
                    @keyup.enter.exact="login"
                />
                <div style="float: left">
                    <q-btn
                        style="width: 87px"
                        :disable="disableBtn"
                        outline
                        color="black"
                        label="로그인"
                        @click="login"
                    />
                </div>
                <div>
                    <router-link to="/register">
                        <q-btn outline color="black" label="회원가입" />
                    </router-link>
                </div>
            </div>
        </q-card-section>
    </q-card>
</template>

<script setup>
import UserApi from "src/common/user/UserApi";
import { computed, onMounted, ref } from "vue";
import router from "src/router";
import { useStore } from "stores/store";
import CommonNotify from "src/common/CommonNotify";

const userId = ref("");
const password = ref("");
const store = useStore;

const login = async () => {
    const data = await UserApi.loginApi(userId.value, password.value);
    if (data.code === 200) {
        localStorage.setItem("AccessToken", data.body.jwtToken.accessToken);
        localStorage.setItem("RefreshToken", data.body.jwtToken.refreshToken);
        store.userInfoData.data.userId = data.body.userId;
        store.userInfoData.data.username = data.body.username;
        store.userInfoData.data.nickname = data.body.nickname;

        CommonNotify.success("환영합니다.");
        await router.push({ name: "todo-list" });
    }

    if (data.code === 401) {
        CommonNotify.fail("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
};

const disableBtn = computed(() => {
    return userId.value.length === 0 || password.value.length === 0;
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
    margin-left: 120px;
}
</style>
