import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import User from '@/components/view/User'
import Home from "@/components/Home";
import Role from "@/components/view/Role";
import Menu from "@/components/view/Menu";

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/',
      name: 'Home',
      component: Home,
      meta: {
        requireAuth: false
      },
      children: [
        {
          path: '',
          component: User
        },
        {
          path: '/role',
          component: Role
        },
        {
          path: 'menu',
          component: Menu
        }
      ]
    }
  ]
});

router.beforeEach((to, from, next) => {
  console.log(to);
  let user = localStorage.getItem("user");
  let requireAuth = to.meta.requireAuth;
  if (user && to.name === 'Login') {
    next({
      path: '/'
    });
  }
  if (requireAuth && requireAuth !== false && !user) {
    console.log("to login path");
    next({
      path: '/login'
    });
  } else {
    console.log("next...");
    next();
  }
});

export default router;
