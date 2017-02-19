import React from 'react';
import Navigator from './Navbar';
import Mem from './Mem';
import DownPanel from './DownPanel';
import Footer from './Footer';
import {Grid,Row,Col} from 'react-bootstrap';

export default class Layout extends React.Component{
    constructor(props) {
        super(props);
        this.state = {memes: []};
        this.showMemesFromPage(0);

    }


    showMemesFromPage(page){

        $.ajax({
            url: '/mem/page/'+page,
            type: 'GET'
        }).then(function(data) {
            this.setState({ memes: data.content });
        }.bind(this));
        window.scrollTo(0, 0);
    }



    render(){
        return (
            <div>
                <Navigator/>
                <Grid>
                    <Row>
                        <Col sm={0} md={1}  lg={1}/>
                        <Col sm={12} md={10} lg={10} className="main-block">
                                {
                                    this.state.memes.map((mem) =>
                                        <Mem title={mem.title} key={'mem-'+mem.id} id={mem.id} fileType={mem.fileType} />
                                    )

                                }
                            <DownPanel showMemesFromPage={this.showMemesFromPage.bind(this)}/>

                        </Col>
                        <Col sm={0} md={1} lg={1}/>
                    </Row>
                </Grid>
                <Footer/>
            </div>
        );
    }
};
