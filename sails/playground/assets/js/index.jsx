import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter as Router, Route, Link, browserHistory, IndexRoute, IndexRedirect } from 'react-router-dom';

    var App = React.createClass({
      render: function () {
        return (
          <div>
            <h1>Order Genie</h1>
            <ul className="header">
              <li>Home</li>
              <li>About</li>
              <li>Contact</li>
            </ul>
            <div className="content">

            </div>
          </div>
        )
      }
    });


    render(
      <Router>
        <Route path="/" component={App} >
        </Route>
      </Router>,

      document.getElementById('container')
    );



