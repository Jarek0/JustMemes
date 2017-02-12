import React from 'react';
import Navigator from './Navbar';
import Mem from './Mem';
import DownPanel from './DownPanel';
import Footer from './Footer';
import {Grid,Row,Col} from 'react-bootstrap';
import axios from 'axios';

export default class Layout extends React.Component{
    constructor(props) {
        super(props);
        this.state = {memes: []};
    }

    componentDidMount() {
        axios.get(`http://www.reddit.com/r/${this.props.subreddit}.json`)
            .then(res => {
                const posts = res.data.data.children.map(obj => obj.data);
                this.setState({ posts });
            });
    }

    render(){
        return (
            <div>
                <Navigator/>
                <Grid>
                    <Row>
                        <Col sm={0} md={1}  lg={1}/>
                        <Col sm={12} md={10} lg={10} className="main-block">
                            <Mem />
                            <Mem />
                            <Mem />
                            <DownPanel />
                        </Col>
                        <Col sm={0} md={1} lg={1}/>
                    </Row>
                </Grid>
                <Footer/>
            </div>
        );
    }
};
