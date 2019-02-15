import {Component} from 'react';
import './App.css';
import router from './router/router'
import {getTimeStamp} from './components/oauth/oauth'

class App extends Component {
    constructor() {
        super();
        this.timestamp = 0;
        this.access_token = '';
    }

    componentWillMount() {
        getTimeStamp(this);
    }

    render() {
        return router;
    }
}

export default App;
