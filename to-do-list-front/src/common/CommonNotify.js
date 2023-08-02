import { Notify } from "quasar";

const success = (message) => {
    return Notify.create({
        message: message,
        type: "positive",
        position: "top",
        progress: true,
    });
};

const fail = (message) => {
    return Notify.create({
        message: message,
        type: "negative",
        position: "top",
        progress: true,
    });
};

export default {
    success,
    fail,
};
