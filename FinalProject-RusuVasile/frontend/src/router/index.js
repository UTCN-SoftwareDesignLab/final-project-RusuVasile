import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import TeamList from "../views/TeamList.vue";
import ContestList from "../views/ContestList.vue";
import ResultList from "../views/ResultList.vue";
import UserTeamPage from "../views/UserTeamPage.vue";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "Teams" });
      }
    },
  },
  {
    path: "/team",
    name: "Teams",
    component: TeamList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isOrganizer) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/userPage",
    name: "Team Page",
    component: UserTeamPage,
    beforeEnter: (to, from, next) => {
      if (store.getters.isUser) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/contest",
    name: "Contests",
    component: ContestList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/results",
    name: "Results",
    component: ResultList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },

  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
