import React from 'react';
import Navigator from './Navbar';
import Mem from './Mem';
import DownPanel from './DownPanel';
import Footer from './Footer';
import {Grid,Row,Col} from 'react-bootstrap';

export default class Layout extends React.Component{

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
