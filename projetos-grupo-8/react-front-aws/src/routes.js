/* eslint-disable no-nested-ternary */
/* eslint-disable react/jsx-no-useless-fragment */
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from "react-router-dom";
import { GlobalProvider } from "./context/GlobalContext";
import useGlobal from "./hooks/useGlobal";
import Home from "./pages/Home";
import Error from "./pages/Upload/Error";
import Sucess from "./pages/Upload/Sucess";

export default function Routes(params) {
  return (
    <Router>
      <Switch>
        <GlobalProvider>
          <Route exact path="/" component={Home} />
          <Route exact path="/error" component={Error} />
          <Route exact path="/sucess" component={Sucess} />
        </GlobalProvider>
      </Switch>
    </Router>
  );
}

function ProtectedRoute(props) {
  const { token } = useGlobal();
  return (
    <Route render={() => (token ? props.children : <Redirect to="/" />)} />
  );
}
